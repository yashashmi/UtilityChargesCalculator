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
        stage ('Deploy')
        {
            steps{
                // sh 'mvn spring-boot:run'
            }
        }
    }
}
