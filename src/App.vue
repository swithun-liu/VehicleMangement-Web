<template>
<div id="app">
  <h1>{{title}}</h1>
    <form>
        <el-input placeholder="请输入内容" v-model="loginForm.id" clearable>
        </el-input>
        <el-input placeholder="请输入内容" v-model="loginForm.password" show-password>
        </el-input>
    </form>
    <el-button type="primary" plain @click="onSubmit">主要按钮</el-button>
</div>
</template>

<script>

import axios from 'axios';
export default {
    name: 'app',
    components: {},
    data() {
        return {
            title: '',
            loginForm: {
                "id": '',
                "password": ''
            }
        }
    },
    mounted() {
    },
    methods: {
        onSubmit() {
            let formData = new FormData();
            for (var key in this.loginForm) {
                formData.append(key, this.loginForm[key])
            }

            axios({
                method: "post",
                url: "http://localhost:8081",
                headers: {
                    "Content-Type": "multipart/form-data"
                },
                withCredentials: true,
                data: formData
            }).then((res) => {
                console.log(res);
            });

        }
    },
}
</script>

<style>
#app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
}
</style>
