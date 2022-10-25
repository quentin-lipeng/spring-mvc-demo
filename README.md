"# spring-mvc-demo" 
# spring mvc + mybatis的web项目
## 主要新功能会更新在dev分支 main为保留分支 以防项目写怀进行rollback
## TODO
shiro的整合

## 删除远程分支文件

`
git rm --cached 文件 //本地中该文件不会被删除
`

`
git rm -r --cached  文件夹 //删除文件夹
`

## 关于提交
因为本项目暂时只有一人开发所以在dev直接进行push

但如果新增开发者将使用单独分支push

项目主要改动在dev分支 主分支为最稳定或者最原始的版本

## 关于tag
- v1.0
    * 为mvc demo 的一小阶段终结 其中包含所有配置方式 
    * v1.0后不会保留v1.0之前多种配置方式
    * v1.0添加了cache功能