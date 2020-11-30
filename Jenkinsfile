pipeline {
  agent {
    label 'Java11'
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

    stage('Trigger UI') {
      steps {
        echo 'Tiggering UI to Build'
        sh '''post 

{
  success {build \'../UtilityChargesCalculatorUI_main\'
  }
}'''
      }
    }

  }
}