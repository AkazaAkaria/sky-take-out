pipeline {
    agent any

    stages {
        stage('编译&打包') {
            steps {
                sh "mvn clean install"
            }
        }
        stage('拷贝jar到目标机器') {
            steps {
               sh "scp ./sky-server/target/sky-server-1.0-SNAPSHOT.jar root@192.168.0.109:/opt"
               sh "scp ./deploy.sh root@192.168.0.109:/opt"
            }
        }
        stage('部署目标机器') {
                    steps {
                      sshPublisher(publishers: [sshPublisherDesc(configName: 'asas', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'chmod +x /opt/deploy.sh && sh /opt/deploy.sh', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
                    }
                }
    }
}
