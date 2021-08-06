node {
    stage("Git cloning.."){
        git url: 'https://github.com/daminduLiyanage/demoTraining.git'
        sh 'ls -al'
    }
    stage("Gradle build "){
        sh 'chmod u+x gradlew'
        sh './gradlew build'
    }
    stage("Docker build"){
        sh 'docker version'
        sh 'docker build -t springboot-kube-demo .'
        sh 'docker image list'
        sh 'docker tag springboot-kube-demo dumi7/springboot-kube-demo:springboot-kube-demo'
    }

    stage("Docker Login"){
        withCredentials([string(credentialsId: 'DOCKER_HUB_PASSWORD', variable: 'PASSWORD')]) {
            sh 'docker login -u dumi7 -p $PASSWORD'
        }
    } 
    stage("Push Image to Docker Hub"){
        sh 'docker push  dumi7/springboot-kube-demo:springboot-kube-demo'
    }
    stage("SSH and Deploy") {
        def remote = [:]
        remote.name = 'kmaster'
        remote.host = '10.0.5.2'
        remote.user = 'vagrant'
        remote.password = 'vagrant'
        remote.allowAnyHosts = true

        stage('Put kube-deployment.yml onto kube master') {
            sshPut remote: remote, from: 'kube-deploy.yml', into: '.'
        }

        stage('Deploy spring boot') {
            sshCommand remote: remote, command: "kubectl apply -f kube-deploy.yml"
        }
    }
}