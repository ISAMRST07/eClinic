<template>
    <div>
        <modify-clinic-dialog
                v-model="addDialog"/>
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
    import ModifyClinicDialog from "./ModifyClinicDialog";

    export default {
        name: "AddClinic",
        components: {ModifyClinicDialog},
        data: () => ({
            addDialog: false
        })

    }
</script>

<style scoped>
    .fab-br {
        position: absolute;
        right: 2em;
        bottom: 3em;
    }

</style>
