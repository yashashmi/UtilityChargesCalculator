pipeline {
  agent {
    label 'Java11'
  }

  environment {
        SONAR_TOKEN = "$env.SONAR_TOKEN"
    }

  stages {
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

    stage('Deploy') {
      steps {
        withCredentials(bindings: [usernamePassword(credentialsId: 'tomcatScriptManager', passwordVariable: 'password', usernameVariable: 'username')]) {
          sh 'mvn tomcat7:redeploy -Dtomcat.username=$username -Dtomcat.password=$password'
        }

      }
    }

    stage('API Testing') {
      steps {
        echo 'API Testing'
        sh 'mvn test -Dtest=ApiTestRunner'
        junit 'target/**/TEST-ApiTestRunner.xml'
      }
    }
  }

  post {
  success {
    echo 'Trigger UI Build'
        build(job: 'UtilityChargesCalculatorUI/main', wait: false)
    }
  }
}