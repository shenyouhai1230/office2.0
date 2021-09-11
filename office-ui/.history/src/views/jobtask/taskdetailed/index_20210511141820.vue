<template>
    <div>
        <el-table :data="list" border >
            <el-table-column align="center" label="姓名" prop="name"></el-table-column>
            <el-table-column align="center" label="年龄" prop="age"></el-table-column>
            <el-table-column align="center" label="爱好" prop="hobby">
                <template slot-scope="scope">
                    <el-select v-model="scope.row[scope.column.property]" multiple="">
                        <el-option 
                            v-for="(row, index) in hobbies"
                            :key="index"
                            :label="row"
                            :value="row"
                        >
                        </el-option>
                    </el-select>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                list: [{
                   name: '张三',
                   age: 18,
                   hobby: ['运动','美食']
                },{
                   name: '里斯',
                   age: 20,
                   hobby: '美食'
                },{
                   name: '利斯特',
                   age: 19,
                   hobby: '宠物'
                }],
                hobbies: ['运动','美食','宠物']
            }
        }
    }
</script>