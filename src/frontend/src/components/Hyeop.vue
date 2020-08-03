<template>
    <div>
<!--        <h1>{{title}}</h1>-->
<!--        <slot></slot>-->
<!--        <p>{{ name }}</p><br>-->
<!--        <button @click="updateName">Change Name</button>-->
        <p>Header</p>
        <slot name="header" :hyeop = "hyeop"></slot>
        <p>Body</p>
        <slot></slot>
        <p>footer</p>
    </div>
</template>

<script>
    export default {
        props: {
            title: {
                type : String,
                // required: true,
                default:"default title"
            }
        },
        data() {
            return {
                hyeop: "Lee Hyeon Hyeop",
                name : 'Kossie Coder',
            }
        },
        methods :{
            updateName(){
                this.name = 'Kossie Coder Updated!';
            }
        }
    }
</script>

<style scoped>

</style>