pipeline {
    agent any
    stages {
        stage('Docker build') {
            steps {
                sh 'docker build -t appfaccat/1.0 .'
            }
        }
        stage('Docker run') {
            steps {
                sh 'docker run -p 8082:8082 appfaccat/1.0 .'
               
            }
        }
       
    }
}
