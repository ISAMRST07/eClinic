<template>
    <div>
        <modify-clinic-room-dialog v-model="addDialog"/>
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
    import ModifyClinicRoomDialog from "./ModifyClinicRoomDialog";
    export default {
        name: "AddClinicRoom",
        components: {ModifyClinicRoomDialog},
        data: () => ({
            addDialog: false
        })
    }
</script>

<style scoped>

</style>
