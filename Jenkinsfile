pipeline {
    agent any
     tools { 
        maven '3.8.5' 
        jdk '8' 
    }
    stages {
        stage('Build') {
            steps {
                sh 'docker build -t appfaccat/1.0 .'
            }
        }
        stage('StopService') {
            steps {
                sh 'docker run -p 8081:8081 appfaccat:1.0 .'
               
            }
        }
       
    }
}
