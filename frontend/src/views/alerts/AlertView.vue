<template>
  <div role="tablist">
    <ul>
      <li v-for="alert in alerts" :key="`alert_${alert.id}`">
        <b-card no-body class="mb-1">
          <b-card-header header-tag="header" class="p-1" role="tab">
            <b-button block v-b-toggle.accordion-{alert.id} variant="light"><h2>{{alert.title}}</h2></b-button>
          </b-card-header>
          <b-collapse id="accordion-{alert.id}" visible accordion="my-accordion" role="tabpanel">
            <b-card-body>
              <b-card-text>{{ alert.content }}</b-card-text>
            </b-card-body>
          </b-collapse>
        </b-card>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";

const SERVER_URL = "";

export default {
  name: "AlertView",
  data() {
    return {
      alerts: []
    };
  },
  methods: {
    fetchAlerts() {
      axios.get(SERVER_URL + "")
        .then(res => this.alerts = res.data)
        .catch(err => console.error(err))
    },
  },
  created() {
    this.fetchAlerts()
  }
};
</script>

<style>
</style>