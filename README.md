# grade
安装:
1.安装JDK1.7<br>
2.安装mysql 6.5+<br>
3.安装tomcat7.0+<br>
4.使用sql/init.sql初始化数据库<br>
5.解压缩run/grade.zip文件 修改WEB-INF/classes/config-base.json 里面的数据库配置为环境的数据库配置<br>
6.拷贝grade文件夹 到tomcat的webapps目录 <br>
7.启动tomcat 监听logs/catalina.out文件看有没有报错<br>
浏览器运行<br>
http://ip:8080/grade/login <br>
管理员：admin/admin<br>
普通用户： cs1/1002  cs2/1003 <br>

也可以自行创建部门，然后创建用户使用 创建新用户默认密码是12345<br>

目前管路员账号登进去 选择测评活动管理-2016年中期考核-查看结果-选择测试组织 可以看到计算出的分数结果<br>
