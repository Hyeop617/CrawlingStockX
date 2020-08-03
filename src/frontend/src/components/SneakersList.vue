<template>
    <div>
        <div>
            <label>환율</label>
            <input type="text" v-model="exchangeRate">
        </div>
        <div>
            <button @click="refresh()">새로고침</button>
        </div>
        <table class="table">
            <tr v-for="(sneakerslist,idx) in rawlist" :key="sneakerslist.size">
                <td>
                    <table class="table" style="height: 100px;">
                        <thead v-if="idx==0">
                        <tr>
                            <th width="250px">이미지</th>
                            <th width="100px">사이즈</th>
                            <th width="100px">가격</th>
                            <th>관세포함(USD)</th>
                            <th>관세포함(KRW)</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tr v-for="(sneakers, index) in sneakerslist.priceList" :key="sneakers.size">
                            <td rowspan="99" v-if="index == 0" width="250px">
                                <img :src="sneakerslist.imgPath" alt="" style="width:250px;">
                                <p>{{sneakerslist.title}}</p>
                            </td>
                            <td width="100px">{{sneakers.size}}</td>
                            <td  width="100px">{{sneakers.price}}</td>
                            <td width="150px">{{sneakers.priceTax}}</td>
                            <td>{{Math.floor(sneakers.priceTax * exchangeRate)}}</td>
                            <td><input class="input" type="text" placeholder="원화 입력" v-model="rawlist[idx].priceList[index].inputPrice" @input="calcDifference(idx,index)"></td>
                            <td>
                                <input type="text" v-model="rawlist[idx].priceList[index].difference" disabled :style="{color :showDifference(idx,index)}">
                            </td>
                        </tr>
                    </table>
                </td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </table>
    </div>
</template>

<script>
    export default {
        mounted() {
            // var vm = this;
            // this.$axios.get("/api/index")
            //     .then(function (response) {
            //         vm.rawlist = response.data;
            //     })
            //     .catch(function (error) {
            //         console.log(error);
            //     })
        },
        methods: {
            calcDifference: function (idx, index) {
                this.rawlist[idx].priceList[index].difference = Math.floor( this.rawlist[idx].priceList[index].inputPrice - (this.rawlist[idx].priceList[index].priceTax * this.exchangeRate));
            },
            showDifference : function(idx, index){
                if(this.rawlist[idx].priceList[index].difference > 0){
                    return "green";
                }else if (this.rawlist[idx].priceList[index].difference == 0){
                    return "black";
                }else{
                    return "red";
                }
            },
            refresh(){
                var vm = this;
                this.$axios.post("/api/update")
                    .then(function (response) {
                        vm.rawlist = response.data;
                    })
                    .catch(function (error) {
                        console.log(error);
                    })
            }

        },
        // watch : {
        //   krw : function () {
        //     this.difference =
        //   }
        // },
        data() {
            return {
                rawlist: [],
                difference: 0,
                exchangeRate: 1
            }
        }
    }
</script>

<style scoped>
    .table {
        width: 1200px;
        height:100px;
        table-layout: fixed;
    }

</style>