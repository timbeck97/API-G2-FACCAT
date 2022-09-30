pipeline {
    agent any
    tools{
        maven "maven"
    }
    stages {
        stage('maven build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true clean package'
            }
        }
        stage('maven run') {
            steps {
                sh './mvnw spring-boot:run'
               
            }
        }
       
    }
}
