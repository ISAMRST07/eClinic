<template>
    <v-container fluid>
        <table-disease></table-disease>
        <add-disease></add-disease>
    </v-container>
</template>

<script>
    import TableDisease from "../components/Disease/TableDisease";
    import AddDisease from "../components/Disease/AddDisease";

    export default {
        name: "Disease",
        components: {AddDisease, TableDisease}
    }
</script>

<style scoped>

</style>
