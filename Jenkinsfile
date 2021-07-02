def TOMCAT_SERVER_IP = "123"
pipeline {
  agent { label 'Java11' }

  environment {
    TOMCAT_CRED = credentials('tomcat-manager')
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
         sh 'mvn -B -DskipTests clean package'
       }
     }

     stage('Tests') {
       steps {
       sh 'mvn -B test'
        junit(testResults: 'target/**/TEST*service*.xml', allowEmptyResults: true)
      }
    }

    stage('Code Analyis') {
      steps {
        echo 'Static Code Analysis'
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
          sh '''folder="JenkinsCasC"
          if ! git clone "${TOMCAT_SETUP_URL}" "${folder}" 2>/dev/null && [ -d "${folder}" ]; then 
            echo "Clone failed because the folder ${folder} exists"
          fi '''
        }

        
        dir('/shared/tomcat/JenkinsCasC/tomcat_slave/tomcat') {
          sh "sed -i 's,password=\"APP_MANAGER_PASSWORD\",password=\"'\"${TOMCAT_CRED_PSW}\"'\",g' tomcat-users.xml"
        }

        dir('/shared/tomcat/JenkinsCasC/tomcat_slave') {
          sh 'sudo docker-compose up -d --build'
        }
      }
    }

    stage('Deploy') {

      steps {
        sh "mvn tomcat7:redeploy -Dtomcat.username=app-manager -Dtomcat.password=${TOMCAT_CRED_PSW} -Dtomcat-url=http://${TOMCAT_SERVER_IP}/manager/text"

      }
    }

    stage('API Testing') {
      steps {
        echo "${TOMCAT_SERVER_IP}"
        echo 'API Testing'
        sh "mvn test -Dtest=ApiTestRunner resources:resources -DbaseUrl=http://${TOMCAT_SERVER_IP}/utilityApp/api/v1/energy/energyCharges"
        junit 'target/**/TEST-ApiTestRunner.xml'
      }
    }
  }

  //   post {
  //   success {
  //     echo 'Trigger UI Build'
  //         build(job: 'UtilityChargesCalculatorUI/main', wait: false)
  //     }
  //   }
}