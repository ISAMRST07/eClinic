<template>
    <v-navigation-drawer
            dark
            class="primary elevation-5"
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
            <v-list-item-group>
                <v-tooltip
                        v-for="(item, i) in items"
                        :key="i"
                        right
                        :disabled="!mini"
                >
                    <template v-slot:activator="{ on }">
                        <v-list-item :to="item.path" link v-on="on">
                            <v-list-item-icon>
                                <v-icon>{{ item.icon }}</v-icon>
                            </v-list-item-icon>

                            <v-list-item-content>
                                <v-list-item-title>{{ item.label }}</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>
                    </template>
                    <span>{{ item.label }}</span>
                </v-tooltip>
            </v-list-item-group>

        </v-list>
    </v-navigation-drawer>
</template>

<script>
    import { ClinicalCenterAdmin } from "../utils/DrawerItems";
    import {mapState} from "vuex";

    export default {
        name: "Drawer",
        data: () => ({
            items: Array,
        }),
        props: {
            mini: {
                type: Boolean,
                default: true
            },
        },
        computed: {
            ...mapState('auth', ['role']),
        },
        mounted() {
            switch (this.role) {
                case ClinicalCenterAdmin.code:
                    this.items = ClinicalCenterAdmin.items;
                    break;
                default:

            }
        }
    }
</script>

<style scoped>
</style>
