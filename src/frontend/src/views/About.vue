<template>
    <div class="about">
        <h1>This is an about page</h1>
        <KossieCoder>
            <template #header="props">
                <h3>this is header {{ props.hyeop}}</h3>
            </template>
            <template #default>
                <h6>default</h6>
            </template>
        </KossieCoder>
    </div>
</template>

<script>
    import KossieCoder from '@/components/Hyeop.vue'

    export default {
        components: {
            KossieCoder
        }
    }
</script>