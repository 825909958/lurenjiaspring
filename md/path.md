1.user.dir字面解释是用户工作目录，实际上因项目的不同运行环境和启动位置有关。

项目运行在Eclipse或IEAD开发工具中，System.getProperty(“user.dir”)的值就是项目目录所在路径。
项目运行在Windows下的tomcat中，System.getProperty(“user.dir”)的值就是tomcat的bin目录所在路径。
项目运行在Linux下的tomcat中，System.getProperty(“user.dir”)的值就是tomcat的根目录所在路径。
若startup.bat或startup.sh启动脚本的执行位置不一样，System.getProperty(“user.dir”)的值就是启动脚本的执行位置所在路径。
如果使用自启脚本etc/systemd/system 下service，取到的则是根目录，，遇到的坑是这个，不知道原因，，或许是shell脚本的启动方法，
