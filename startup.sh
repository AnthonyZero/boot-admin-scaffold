nohup java -jar admin-scaffold.jar --spring.profiles.active=pro --server.port=8080 >logs/admin-scaffold.log 2>&1 &

tail -f logs/admin-scaffold.log