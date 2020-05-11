<template>
    <router-view/>
</template>

<script>

    import Main from "./views/Main";

    export default {
        name: 'App',

        components: {
            Main,
        },

        data: () => ({
            //
        }),
    };
</script>

<style>
    ::-webkit-scrollbar {
        width: 0 !important;
        background: transparent !important;
        overflow: hidden !important;
    }

    .fab-br {
        position: absolute;
        right: 2em;
        bottom: 3em;
        z-index: 20;
    }
</style>
