<template>
    <v-container fluid>
        <table-diagnosis></table-diagnosis>
        <add-diagnosis></add-diagnosis>
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
