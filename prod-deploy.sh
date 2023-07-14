# maven -> profiles -> prod -> clean -> install
scp -P 2201 ./jeecg-module-system/jeecg-system-start/target/jeecg-system-start-3.4.4.jar root@82.156.168.160:/root/jeecg-boot/docker
ssh -p 2201 root@82.156.168.160 "cd /root/jeecg-boot/docker && docker build -t jeecg-boot . && cd .. && docker-compose up -d"
