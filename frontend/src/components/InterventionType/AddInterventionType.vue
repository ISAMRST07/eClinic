<template>
    <div>
        <modify-intervention-type-dialog v-model="addDialog"/>
        <v-btn
                fab
                dark
                large
                color="red"
                class="fab-br"
                @click.stop="addDialog = true"
        >
            <v-icon> mdi-plus </v-icon>
        </v-btn>
    </div>
</template>

<script>
    import ModifyInterventionTypeDialog from "./ModifyInterventionTypeDialog";
    export default {
        name: "AddInterventionType",
        components: {ModifyInterventionTypeDialog},
        data: () => ({
            addDialog: false
        })
    }
</script>

<style scoped>

</style>
