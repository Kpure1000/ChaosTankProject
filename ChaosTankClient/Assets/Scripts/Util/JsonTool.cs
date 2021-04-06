using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
namespace Util
{
    public class JsonTool
    {

        public JsonTool()
        {
        }

        public static string ObjectToJsonStr<T>(T obj)
        {
            return JsonConvert.SerializeObject(obj);
        }

        public static byte[] ObjectToJsonByte<T>(T obj)
        {
            byte[] dataBf;
            dataBf = Encoding.UTF8.GetBytes(ObjectToJsonStr(obj));

            byte[] sendBytes = new byte[1024];
            for (int i = 0; i < dataBf.Length; i++)
            {
                sendBytes[i] = dataBf[i];
            }
            return sendBytes;
        }

        public static T JsonStrToObject<T>(string jsonStr)
        {
            return JsonConvert.DeserializeObject<T>(jsonStr);
        }

        public static T JsonByteToObject<T>(byte[] jsonByte)
        {
            string jsonStr = jsonByte.ToString();
            return JsonStrToObject<T>(jsonStr);
        }
    }
}


