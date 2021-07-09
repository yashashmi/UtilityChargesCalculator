def TOMCAT_SERVER_IP = "123"
pipeline {
  agent { label 'Java11' }

  environment {
    TOMCAT_CRED = credentials('tomcat-manager')
    APP_ENGINE_SERVICE = credentials('AppEngineServiceKey')
    SONAR_TOKEN = "$env.SONAR_TOKEN"
    TOMCAT_SETUP_URL = 'https://github.com/yashashmi/JenkinsCasC.git'
  }

  stages {
    stage('SCM Checkout') {
      steps {
        checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/yashashmi/UtilityChargesCalculator.git']]])
      }
    }
     stage('Build') {
       steps {
         sh 'mvn -B -DskipTests clean compile'
       }
     }

     stage('Unit Tests') {
       steps {
       sh 'mvn -B test'
        junit(testResults: 'target/**/TEST*service*.xml', allowEmptyResults: true)
      }
    }

    stage('Code Analyis') {
      steps {
        sh 'mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar'

      }
    }

    stage('Provision') {
      agent { label 'tomcat85' }

      steps {
        script {
          TOMCAT_SERVER_IP = sh(script: 'curl api.ipify.org', returnStdout: true).trim()
        }

        echo "${TOMCAT_SERVER_IP}"

        //checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/yashashmi/UtilityChargesCalculator.git']]])
        sh 'sudo chown -R jenkins:jenkins /shared'
        dir('/shared/tomcat') {
          sh '''
          folder="JenkinsCasC"
          if ! git clone "${TOMCAT_SETUP_URL}" "${folder}" 2>/dev/null && [ -d "${folder}" ]; then 
            echo "Clone failed because the folder ${folder} exists"
          fi 
          '''
        }

        
        dir('/shared/tomcat/JenkinsCasC/tomcat_slave/tomcat') {
          sh '''
          sed -i 's,password=\"APP_MANAGER_PASSWORD\",password=\"'${TOMCAT_CRED_PSW}'\",g' tomcat-users.xml
          '''
        }

        dir('/shared/tomcat/JenkinsCasC/tomcat_slave') {
          sh 'sudo docker-compose up -d --build'
        }

      }
    }

    stage('Deploy') {
      environment{
        def url = "http://${TOMCAT_SERVER_IP}/manager/text"
      }
      steps {
        
        sh '''
        mvn tomcat7:redeploy -DpackagingType=war -Dtomcat.username=app-manager -Dtomcat.password=${TOMCAT_CRED_PSW} -Dtomcat-url=${url}
        '''

      }
    }

  stage ('Tests in Parallel')
  {
    parallel
    {

    stage('API Tests') {
      steps {
        echo "${TOMCAT_SERVER_IP}"
        sh "mvn test -Dtest=ApiTestRunner resources:resources -DbaseUrl=http://${TOMCAT_SERVER_IP}/utilityApp/energyCharges"
        junit 'target/**/TEST-ApiTestRunner.xml'
        cucumber failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '**/*.json', jsonReportDirectory: 'target/cucumber-reports/API/', pendingStepsNumber: -1, reportTitle: 'API Tests Result', skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
     
      }
    }

        stage('Selenium Tests') {
      steps {
        echo "Chrome and ChromeDriver setup"
        sh '''
              sudo apt-get install -y unzip
              sudo curl -sS -o - https://dl-ssl.google.com/linux/linux_signing_key.pub | sudo apt-key add
              sudo sh -c 'echo "deb [arch=amd64]  http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list'
              sudo apt-get -y update
              sudo apt-get -y install google-chrome-stable

              export CHROME_VERSION=$(curl https://chromedriver.storage.googleapis.com/LATEST_RELEASE)
              wget https://chromedriver.storage.googleapis.com/${CHROME_VERSION}/chromedriver_linux64.zip
              unzip chromedriver_linux64.zip

              sudo mv chromedriver /usr/bin/chromedriver
              sudo chown root:root /usr/bin/chromedriver
              sudo chmod +x /usr/bin/chromedriver

        '''
        sh "mvn test -Dtest=SeleniumTestRunner resources:resources -DbaseUrl=http://${TOMCAT_SERVER_IP}/utilityApp"
        junit 'target/**/TEST-SeleniumTestRunner.xml'
        cucumber failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '**/*.json', jsonReportDirectory: 'target/cucumber-reports/Selenium/', pendingStepsNumber: -1, reportTitle: 'Selenium Tests Result', skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
     
      }
    }
    }
  }

        stage('Deploy to App Engine') {
      steps {
        sh 'mvn clean package'
         sh 'gcloud auth activate-service-account --key-file ${APP_ENGINE_SERVICE};'
         sh 'gcloud app deploy'
        //sh "mvn clean package appengine:deploy -Dapp.deploy.projectId=my-second-project-314314 -Dapp.deploy.version=2 -Dappengine.additionalParams=--service_account_json_key_file=${APP_ENGINE_SERVICE}"
      }
    }


  }
}