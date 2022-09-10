pipeline {
    agent any
     tools { 
        maven 'Maven 3.3.9' 
        jdk 'jdk8' 
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true install'
            }
        }
        stage('StopService') {
            steps {
                sh 'chmod +x stopService.sh'
                sh './stopService.sh'
            }
        }
        stage('Deploy') {
            steps {
                sh 'java -jar /var/jenkins_home/workspace/api-jenkins/target/demo-0.0.1-SNAPSHOT.jar --server.port=8081'
            }
        }
    }
}
