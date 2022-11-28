<template>
  <div class="about" @click="_login">
    <!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
    aaaa
  </div>

  <div class="about" @click="count">
    <!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
    bbb
  </div>

</template>

<script>
import {user, count} from "@/api/User.js";
import {setToken} from '@/untils/auth'

export default {
  methods: {
    count() {
      count().then(res => {
        console.log(res.data)
      }).catch(err => {
        console.log(err.data)
      })
    },
    // 登录
    _login() {
      // axios({
      //   url:"http://localhost:8080/login"
      // }).then(result => {
      //   console.log(result);
      // })
        user({
          'username': 'admin',
          'password': 'admin123'
        }).then(res => {
          console.log(res);
          setToken(res.token)
          // commit('SET_TOKEN', res.token)
        }).catch(err => {
        })
    }
  }
}
</script>
