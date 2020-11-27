pipeline {
     agent {label 'Java11'}
    
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
         stage('Tests') { 
            steps {
                sh 'mvn -B test' 
            }
        }
        stage ('Deploy') {
            steps{
               withCredentials([usernamePassword(credentialsId: 'tomcatScriptManager', passwordVariable: 'password', usernameVariable: 'username')]) {
    sh 'mvn tomcat7:redeploy -Dtomcat.username=$username -Dtomcat.password=$password'

                }
               
            }
        }
    }
}
