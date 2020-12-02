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
  }

  post {
  success {
    echo 'Trigger UI Build'
        build(job: 'UtilityChargesCalculatorUI/main', wait: false)
    }

  failure {
        mail to: 'yas.hashmi@gmail.com',
             subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
             body: "Something is wrong with ${env.BUILD_URL}"
    }

     changed {

            mail to: 'yas.hashmi@gmail.com',
             subject: "Pipeline Status Changed: ${currentBuild.fullDisplayName}",
             body: "Pipeline status has changed since last build ${env.BUILD_URL}"
    }
  }
}