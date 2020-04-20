<template>
    <v-container fluid>
        <v-dialog v-model="addDialog" fullscreen hide-overlay transition="dialog-bottom-transition">
            <v-card>
                <v-toolbar dark color="accent">
                    <v-btn icon dark @click="addDialog = false">
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                    <v-toolbar-title>Add a clinic</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-toolbar-items>
                        <v-btn dark text @click="addDialog = false">Add</v-btn>
                    </v-toolbar-items>
                </v-toolbar>
                <v-container>
                    <v-row>
                        <v-col cols="12" md="6">
                            <v-row>
                                <v-col cols="12" align-self="end">
                                    <v-text-field label="Name*" required></v-text-field>
                                </v-col>
                                <v-col cols="12" sm="6">
                                    <v-text-field label="Address*" required>
                                        <v-tooltip bottom slot="append-outer">
                                            <template v-slot:activator="{ on }">
                                                <v-btn icon v-on="on" class="d-none d-md-flex"><v-icon>mdi-map</v-icon></v-btn>
                                                <v-btn icon v-on="on" class="d-flex d-md-none"><v-icon>mdi-map</v-icon></v-btn>
                                            </template>
                                            <span>View on the map</span>
                                        </v-tooltip>
                                    </v-text-field>
                                </v-col>
                                <v-col cols="12" sm="6">
                                    <v-text-field label="Coordinates" disabled></v-text-field>
                                </v-col>
                                <v-col cols="12">
                                    <v-textarea
                                            counter
                                            outlined
                                            label="Description"
                                            rows="10"
                                            :rules="rules"
                                    ></v-textarea>
                                </v-col>
                            </v-row>
                        </v-col>
                        <v-col cols="6">
                            <map-view class="d-none d-md-flex"/>
                        </v-col>
                    </v-row>
                    <small>*indicates required field</small>
                </v-container>
                </v-card>
        </v-dialog>
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
    </v-container>
</template>

<script>
    import MapView from "../components/MapView";
    export default {
        name: "Clinics",
        components: {MapView},
        data: () => ({
            addDialog: false,
            rules: [v => v?.length <= 255 || 'Max 256 characters.'],

        }),
    }
</script>

<style scoped>
    .fab-br {
        position: absolute;
        right: 2em;
        bottom: 3em;
    }

</style>
