<!--<template>-->
<!--  <div class="home">-->
<!--    <img alt="Vue logo" src="../assets/logo.png">-->
<!--    <HelloWorld msg="Welcome to Your Vue.js App"/>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--// @ is an alias to /src-->
<!--import HelloWorld from '@/components/HelloWorld.vue'-->

<!--export default {-->
<!--  name: 'Home',-->
<!--  components: {-->
<!--    HelloWorld-->
<!--  }-->
<!--}-->
<!--</script>-->

<template>
    <div>
        <h1>Hello Hyeop!!!</h1>
        <Kossie/>
        <br>
        
        <form action="">
            <InputField v-model="name"/>
            <button>Submit</button>
        </form>
        {{ name }}
    </div>
</template>

<script>
    import Kossie from '@/components/Hyeop.vue';
    import InputField from "../components/InputField.vue";

    export default {
        components : {
            InputField,
          Kossie
        },
        data(){
            return {
                name : ''
            }
        },
        // methods:{
        //     updateName(name){
        //         this.name = name;
        //     }
        // }
    }
</script>

<style scoped>
    h1 {
        font-size: 60px;
        color : #823b32
    }
</style>