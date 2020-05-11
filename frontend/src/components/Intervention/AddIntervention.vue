<template>
    <div>
        <modify-intervention-dialog v-model="addDialog"/>
        <v-btn
                fab
                dark
                large
                color="red"
                class="fab-br"
                @click.stop="addDialog = true"
        >
            <v-icon> mdi-plus</v-icon>
        </v-btn>
    </div>
</template>

<script>
    import ModifyInterventionDialog from "./ModifyInterventionDialog";

    export default {
        name: "AddIntervention",
        components: {ModifyInterventionDialog},
        data: () => ({
            addDialog: false
        })
    }
</script>

<style scoped>

</style>
