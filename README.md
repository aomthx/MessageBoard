# MessageBoard（Spring Boot + MyBatis + MySQL）

该仓库已完成后端重构：
- 技术栈：Spring Boot 3 + MyBatis（兼容 MyBatisX 插件生成/维护 Mapper）+ MySQL
- 前端 `web/` 目录暂未改动，可后续再迁移

## 启动步骤
1. 准备 MySQL，并执行 `sql/messageboard.sql` 初始化数据库。
2. 修改 `src/main/resources/application.yml` 中数据库账号密码。
3. 运行：
   ```bash
   mvn spring-boot:run
   ```

## 已迁移接口（保持原 URL）
- 用户与认证：`/login` `/register` `/resetPassword` `/signOut`
- 留言：`/printMessages` `/searchPost` `/homePage` `/addPost` `/deletePost`
- 评论：`/addComment` `/deleteComment`
- 管理：`/ad_login` `/banUser`
- 辅助：`/sendEmailCode` `/createCode`

> 注意：`/sendEmailCode` 在重构阶段先返回验证码生成成功提示，不直接发送邮件。
