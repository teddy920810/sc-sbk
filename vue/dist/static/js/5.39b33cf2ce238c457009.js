webpackJsonp([5],{"0ffu":function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container"},[a("el-form",[a("el-form-item")],1)],1),t._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading.body",value:t.listLoading,expression:"listLoading",modifiers:{body:!0}}],attrs:{data:t.list,"element-loading-text":"拼命加载中",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"80"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{domProps:{textContent:t._s(t.getIndex(e.$index))}})]}}])}),t._v(" "),a("el-table-column",{staticStyle:{width:"60px"},attrs:{align:"center",prop:"nickName",label:"昵称"}}),t._v(" "),a("el-table-column",{staticStyle:{width:"60px"},attrs:{align:"center",prop:"nickName",label:"总公里"}}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"创建时间",width:"170"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.createdDateTime))])]}}])}),t._v(" "),t.hasPerm("article:update")?a("el-table-column",{attrs:{align:"center",label:"管理",width:"200"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"primary",icon:"edit"},on:{click:function(a){t.showUpdate(e.$index)}}},[t._v("查看打卡记录")])]}}])}):t._e()],1),t._v(" "),a("el-pagination",{attrs:{"current-page":t.listQuery.pageNum,"page-size":t.listQuery.pageRow,total:t.totalCount,"page-sizes":[10,20,50,100],layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}}),t._v(" "),a("el-dialog",{attrs:{title:"用户打卡记录",visible:t.dialogFormVisible,width:"60%"},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-table",{directives:[{name:"loading",rawName:"v-loading.body",value:t.runDataListLoading,expression:"runDataListLoading",modifiers:{body:!0}}],attrs:{data:t.runDataList,"element-loading-text":"拼命加载中",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"80"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",{domProps:{textContent:t._s(t.getRunDataIndex(e.$index))}})]}}])}),t._v(" "),a("el-table-column",{staticStyle:{width:"60px"},attrs:{align:"center",prop:"totalMile",label:"公里数"}}),t._v(" "),a("el-table-column",{staticStyle:{width:"60px"},attrs:{align:"center",prop:"speed",label:"配速"}}),t._v(" "),a("el-table-column",{staticStyle:{width:"60px"},attrs:{align:"center",prop:"timeCost",label:"用时"}}),t._v(" "),a("el-table-column",{staticStyle:{width:"60px"},attrs:{align:"center",label:"图片"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-popover",{attrs:{trigger:"hover",placement:"top"}},[a("img",{attrs:{src:e.row.imageData}}),t._v(" "),a("div",{staticClass:"name-wrapper",attrs:{slot:"reference"},slot:"reference"},[a("el-tag",{attrs:{size:"medium"}},[t._v("图片")])],1)])]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"打卡时间",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.runningDateTime))])]}}])}),t._v(" "),t.hasPerm("article:update")?a("el-table-column",{attrs:{align:"center",label:"管理",width:"200"},scopedSlots:t._u([{key:"default",fn:function(e){return[0==e.row.status?a("el-button",{attrs:{type:"primary",icon:"edit"},on:{click:function(a){t.updateRunData(e.row.id,1)}}},[t._v("通过")]):t._e(),t._v(" "),0==e.row.status?a("el-button",{attrs:{type:"danger",icon:"edit"},on:{click:function(a){t.updateRunData(e.row.id,-1)}}},[t._v("不通过")]):t._e()]}}])}):t._e()],1),t._v(" "),a("el-pagination",{attrs:{"current-page":t.runDataListQuery.pageNum,"page-size":t.runDataListQuery.pageRow,total:t.runDataTotalCount,"page-sizes":[10,20,50,100],layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)],1)},staticRenderFns:[]},i=a("VU/8")({data:function(){return{totalCount:0,list:[],listLoading:!1,listQuery:{pageNum:1,pageRow:50,name:""},dialogStatus:"create",dialogFormVisible:!1,runDataList:[],runDataListLoading:!1,runDataTotalCount:0,runDataListQuery:{pageNum:1,pageRow:10,name:""}}},created:function(){this.getList()},methods:{getList:function(){var t=this;this.hasPerm("article:list")&&(this.listLoading=!0,this.api({url:"/sys/wxUser/",method:"get",params:this.listQuery}).then(function(e){t.listLoading=!1,t.list=e.list,t.totalCount=e.totalCount}))},handleSizeChange:function(t){this.listQuery.pageRow=t,this.handleFilter()},handleCurrentChange:function(t){this.listQuery.pageNum=t,this.getList()},getIndex:function(t){return(this.listQuery.pageNum-1)*this.listQuery.pageRow+t+1},showUpdate:function(t){var e=this;this.runDataListQuery.wxUserId=this.list[t].id,this.runDataListQuery.groupId=1,this.runDataListLoading=!0,this.api({url:"/sys/runningData/",method:"get",params:this.runDataListQuery}).then(function(t){e.runDataListLoading=!1,e.runDataList=t.list,e.runDataTotalCount=t.totalCount}),this.dialogFormVisible=!0},getRunDataIndex:function(t){return(this.runDataListQuery.pageNum-1)*this.runDataListQuery.pageRow+t+1},updateRunData:function(t,e){var a=this;this.api({url:"/sys/runningData/check",method:"post",params:{runDataId:t,status:e}}).then(function(t){a.dialogFormVisible=!1})}}},n,!1,null,null,null);e.default=i.exports}});