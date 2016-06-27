# github 使用说明
本次考核提交的代码使用 git 版本库来管理  
github 提供了 git 版本库相关的一系列服务，我们直接使用它（github）来提交代码

## github 注册 github 账号
访问 https://github.com ,  点击 “Sign up” 进行注册  
注册第一步，输入 Username, Email Address, Password, 点击 “Create an account”  
注册第二步，Choose your personal plan 选择 Unlimited public repositories for free. 点击 “Continue”  
注册第二步，可以点击 “skip this step” 跳过  
至此，注册完成  

## Fork 测验工程
访问 https://github.com/fushang318/team-teacher-student，点击右上角的 “Fork”  
这样就把测验工程Fork到自己账号下面了  


## 本地系统安装 git
访问 https://git-scm.com/downloads, 根据自己使用的系统来安装 git  

如果你的系统是 linux 或者 mac os 应该都可以很容易的顺利安装  
如果你的系统是 windows，可能安装包的下载地址是被墙的，不过没关系，你可以访问这个地址下载  http://pan.baidu.com/s/1gffRoER


## 迁出测验代码到本地系统
访问你 fork 的测验工程的 github 主页，点击页面上的绿色按钮 “Clone or download”  
复制 https 开头的这个地址  https://github.com/<your-account-name>/team-teacher-student.git  

命令行运行如下命令（使用 windows 的同学，使用 git bash 进入命令行，其实就是一个 MinGW）  
```bash
git clone https://github.com/<your-account-name>/team-teacher-student.git
cd team-teacher-student # 该目录就是你的工程目录了，你可以在该目录下进行开发和提交代码
```

## 提交代码
```bash
cd team-teacher-student
git add -A
git commit -m "input your commit message"
```

## push 代码到 github
```bash
cd team-teacher-student
git push origin master
# 输入你的账户名和密码
# 提交完成后，访问你 fork 的测验工程的 github 主页就会看到你的提交已经 push 到 github 了
```

## 提交 pull request
如果你确定自己已经开发完成，那么就可以提交 pull request 了  
访问你 fork 的测验工程的 github 主页，点击页面上的 “pull requests”，再点击绿的的 “New pull request” 按钮  
输入你的提交说明，提交说明应该包括  
1 你开发的功能使用了哪些技术  
2 如何把你的代码运行起来  
3 你的名字  

至此你就可以等待考核结果了
