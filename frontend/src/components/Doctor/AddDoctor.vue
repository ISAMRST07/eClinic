<template>
    <div>
        <modify-doctor-dialog v-model="addDialog"/>
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
    import ModifyDoctorDialog from "./ModifyDoctorDialog";
    export default {
        name: "AddDoctor",
        components: {ModifyDoctorDialog},
        data: () => ({
            addDialog: false
        })
    }
</script>

<style scoped>

</style>
