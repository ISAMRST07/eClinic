<template>
    <v-app id="inspire">
        <v-content>
            <login-dialog/>
        </v-content>
    </v-app>
</template>

<script>
    import {emailPattern} from "../utils/skeletons";
    import {mapActions, mapGetters, mapState} from "vuex";
    import LoginDialog from "../components/Auth/LoginDialog";

    export default {
        name: "Login",
        components: {LoginDialog},
    }
</script>

<style scoped>

</style>
