<template>
    <v-row class="fill-height">
        <v-col>
            <v-sheet height="64">
                <v-toolbar color="white" flat>
                    <v-btn @click="setToday" class="mr-4" color="grey darken-2" outlined>
                        Today
                    </v-btn>
                    <v-btn @click="prev" color="grey darken-2" fab small text>
                        <v-icon small>mdi-chevron-left</v-icon>
                    </v-btn>
                    <v-btn @click="next" color="grey darken-2" fab small text>
                        <v-icon small>mdi-chevron-right</v-icon>
                    </v-btn>
                    <v-toolbar-title>{{ title }}</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-menu bottom right>
                        <template v-slot:activator="{ on }">
                            <v-btn
                                    color="grey darken-2"
                                    outlined
                                    v-on="on"
                            >
                                <span>{{ typeToLabel[type] }}</span>
                                <v-icon right>mdi-menu-down</v-icon>
                            </v-btn>
                        </template>

                        <v-list>
                            <v-list-item @click="type = 'day'">
                                <v-list-item-title>Day</v-list-item-title>
                            </v-list-item>
                            <v-list-item @click="type = 'week'">
                                <v-list-item-title>Week</v-list-item-title>
                            </v-list-item>
                            <v-list-item @click="type = 'month'">
                                <v-list-item-title>Month</v-list-item-title>
                            </v-list-item>
                            <v-list-item @click="type = '4day'">
                                <v-list-item-title>4 days</v-list-item-title>
                            </v-list-item>
                        </v-list>
                    </v-menu>
                </v-toolbar>
            </v-sheet>
            <v-sheet height="600">
                <v-calendar
                        :event-color="getEventColor"
                        :events="events"
                        :type="type"
                        @change="updateRange"
                        @click:date="viewDay"
                        @click:event="showEvent"
                        @click:more="viewDay"
                        color="primary"
                        ref="calendar"
                        v-model="focus"
                ></v-calendar>
                <v-menu
                        :activator="selectedElement"
                        :close-on-content-click="false"
                        offset-x
                        v-model="selectedOpen"
                >
                    <v-card
                            color="grey lighten-4"
                            flat
                            min-width="350px"
                    >
                        <v-toolbar
                                :color="selectedEvent.color"
                                dark
                        >
                            <v-btn icon>
                                <v-icon>mdi-pencil</v-icon>
                            </v-btn>
                            <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
                            <v-spacer></v-spacer>
                            <v-btn icon>
                                <v-icon>mdi-heart</v-icon>
                            </v-btn>
                            <v-btn icon>
                                <v-icon>mdi-dots-vertical</v-icon>
                            </v-btn>
                        </v-toolbar>
                        <v-card-text>
                            <span v-html="selectedEvent.details"></span>
                        </v-card-text>
                        <v-card-actions>
                            <v-btn
                                    @click="selectedOpen = false"
                                    color="secondary"
                                    text
                            >
                                Cancel
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-menu>
            </v-sheet>
        </v-col>
    </v-row>
</template>


<script>
    export default {
        name: "WorkingCalendar",
        data: () => ({
            focus: '',
            type: 'month',
            typeToLabel: {
                month: 'Month',
                week: 'Week',
                day: 'Day',
                '4day': '4 Days',
            },
            start: null,
            end: null,
            selectedEvent: {},
            selectedElement: null,
            selectedOpen: false,
            events: [],
            colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
            names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
        }),
        computed: {
            title() {
                const {start, end} = this
                if (!start || !end) {
                    return ''
                }

                const startMonth = this.monthFormatter(start)
                const endMonth = this.monthFormatter(end)
                const suffixMonth = startMonth === endMonth ? '' : endMonth

                const startYear = start.year
                const endYear = end.year
                const suffixYear = startYear === endYear ? '' : endYear

                const startDay = start.day + this.nth(start.day)
                const endDay = end.day + this.nth(end.day)

                switch (this.type) {
                    case 'month':
                        return `${startMonth} ${startYear}`
                    case 'week':
                    case '4day':
                        return `${startMonth} ${startDay} ${startYear} - ${suffixMonth} ${endDay} ${suffixYear}`
                    case 'day':
                        return `${startMonth} ${startDay} ${startYear}`
                }
                return ''
            },
            monthFormatter() {
                return this.$refs.calendar.getFormatter({
                    timeZone: 'UTC', month: 'long',
                })
            },
        },
        mounted() {
            this.$refs.calendar.checkChange()
        },
        methods: {
            viewDay({date}) {
                this.focus = date
                this.type = 'day'
            },
            getEventColor(event) {
                return event.color
            },
            setToday() {
                this.focus = this.today
            },
            prev() {
                this.$refs.calendar.prev()
            },
            next() {
                this.$refs.calendar.next()
            },
            showEvent({nativeEvent, event}) {
                const open = () => {
                    this.selectedEvent = event
                    this.selectedElement = nativeEvent.target
                    setTimeout(() => this.selectedOpen = true, 10)
                }

                if (this.selectedOpen) {
                    this.selectedOpen = false
                    setTimeout(open, 10)
                } else {
                    open()
                }

                nativeEvent.stopPropagation()
            },
            updateRange({start, end}) {
                const events = []

                const min = new Date(`${start.date}T00:00:00`)
                const max = new Date(`${end.date}T23:59:59`)
                const days = (max.getTime() - min.getTime()) / 86400000
                const eventCount = this.rnd(days, days + 20)

                for (let i = 0; i < 1; i++) {
                    const allDay = this.rnd(0, 3) === 0
                    const firstTimestamp = this.rnd(min.getTime(), max.getTime())
                    console.log(firstTimestamp)
                    const first = new Date(firstTimestamp - (firstTimestamp % 900000))
                    const secondTimestamp = this.rnd(2, allDay ? 288 : 8) * 900000
                    const second = new Date(first.getTime() + secondTimestamp)

                    events.push({
                        details: "CAO SVIMA",
                        name: "HCI",
                        start: this.formatDate(first, !allDay),
                        end: this.formatDate(second, !allDay),
                        color: this.colors[this.rnd(0, this.colors.length - 1)],
                    })
                }

                this.start = start
                this.end = end
                this.events = events
            },
            nth(d) {
                return d > 3 && d < 21
                    ? 'th'
                    : ['th', 'st', 'nd', 'rd', 'th', 'th', 'th', 'th', 'th', 'th'][d % 10]
            },
            rnd(a, b) {
                return Math.floor((b - a + 1) * Math.random()) + a
            },
            formatDate(a, withTime) {
                console.log(withTime
                    ? `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()} ${a.getHours()}:${a.getMinutes()}`
                    : `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()}`);
                return withTime
                    ? `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()} ${a.getHours()}:${a.getMinutes()}`
                    : `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()}`
            },
        },
    }
</script>

<style scoped>

</style>