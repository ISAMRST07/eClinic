<template>
    <v-navigation-drawer
            dark
            class="cyan elevation-5"
            :mini-variant="mini"
            permanent
            app
    >
        <v-list>
            <v-tooltip :disabled="!mini" right>
                <template v-slot:activator="{ on }">
                    <v-list-item link v-on="on" @click.stop="$emit('miniVariant')">
                        <v-list-item-icon>
                            <v-icon>mdi-menu</v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title>Collapse</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </template>
                <span>Expand</span>
            </v-tooltip>

            <v-tooltip
                    v-for="([icon, text], i) in items"
                    :key="i"
                    right
                    :disabled="!mini"
            >
                <template v-slot:activator="{ on }">
                    <v-list-item link v-on="on">
                        <v-list-item-icon>
                            <v-icon>{{ icon }}</v-icon>
                        </v-list-item-icon>

                        <v-list-item-content>
                            <v-list-item-title>{{ text }}</v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </template>
                <span>{{ text }}</span>
            </v-tooltip>


        </v-list>
    </v-navigation-drawer>
</template>

<script>
    import { ClinicalCenterAdminItems } from "../utils/DrawerItems";

    export default {
        name: "Drawer",
        data: () => ({
            items: Array,
        }),
        props: {
            role: {
                type: String,
                // PRIVREMENO
                default: 'ClinicalCenterAdmin'
            },
            mini: {
                type: Boolean,
                default: true
            },
        },
        created() {
            switch (this.role) {
                case 'ClinicalCenterAdmin':
                    this.items = ClinicalCenterAdminItems;
                    break;
                default:

            }
        }
    }
</script>

<style scoped>

</style>
