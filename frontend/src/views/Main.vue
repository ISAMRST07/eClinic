<template>
    <v-app>
        <drawer :mini="miniDrawer" @miniVariant="miniDrawer = !miniDrawer"/>
        <nav-bar/>
        <v-content>
        </v-content>
    </v-app>
</template>

<script>
    import NavBar from "../components/NavBar";
    import Drawer from "../components/Drawer";

    export default {
        name: "Home",
        components: {Drawer, NavBar},
        data: () => ({
            miniDrawer: true,
        }),
        computed: {
            model() {

            },
        },
    }
</script>

<style scoped>

</style>
