import { reactive, readonly } from 'vue'
import { ref } from 'vue'

const info = ref<String>('')

export function useInfo(){
    function loescheInfo(){
        info.value = ''
    }
    function setzeInfo(msg: String){
        info.value = msg
    }

    return {
        info: readonly(info),
        loescheInfo,
        setzeInfo
    }


}

