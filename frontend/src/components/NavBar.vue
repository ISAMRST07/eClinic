<template>
    <v-app-bar
            absolute
            color="white"
            flat
            app
    >
        <v-toolbar-title>eClinic</v-toolbar-title>

        <v-spacer></v-spacer>
        <v-btn icon>
            <v-icon>mdi-apps</v-icon>
        </v-btn>
        <v-btn icon class="ml-1 mr-2">
            <v-avatar color="accent">
                <span class="white--text headline">A</span>
            </v-avatar>
        </v-btn>
    </v-app-bar>
</template>

<script>
    export default {
        name: "NavBar",
    }
</script>

<style scoped>

</style>
