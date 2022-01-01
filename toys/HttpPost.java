
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;

public class HttpPost {

    public int GetWithCode(String oriUrl){

        try {
            URL theUrl = new URL(oriUrl);
            URLConnection connection = theUrl.openConnection();

            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type","application/json");
            httpURLConnection.setRequestProperty("Accept","*/*");

            httpURLConnection.connect();

            int code = httpURLConnection.getResponseCode();

            return code;
        }
        catch (Exception e){
            return -300;
        }
    }

    //发起Get的同时获得服务器返回信息
    public String GetWithRespond(String oriUrl){

        try {
            URL theUrl = new URL(oriUrl);
            URLConnection connection = theUrl.openConnection();

            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestMethod("GET");
           // httpURLConnection.setDoOutput(true); //当设置这个的时候请求莫名其妙会变成post
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Content-Type","application/json");
            httpURLConnection.setRequestProperty("Accept","*/*");

            httpURLConnection.connect();

            int code = httpURLConnection.getResponseCode();

            if(httpURLConnection.getResponseCode() == 200){
                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine())!= null){
                    stringBuilder.append(line);
                }
                bufferedReader.close();
                inputStreamReader.close();

                String result = stringBuilder.toString();
                httpURLConnection.disconnect();
                return result;
            }
            else {
                return null;
            }

        }
        catch (Exception e){
            return null;
        }
    }

      //发送PostJson请求
      public int PostJson(byte[] BodyJson, String oriUrl){

        try{
            byte[] test = BodyJson;
            int length = test.length;

            URL theUrl = new URL(oriUrl);
            URLConnection connection = theUrl.openConnection();

            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type","application/json");
            httpURLConnection.setRequestProperty("Accept","*/*");
            httpURLConnection.setFixedLengthStreamingMode(length);

            httpURLConnection.connect();

            try(OutputStream os = httpURLConnection.getOutputStream()){
                os.write(test);
            }

            int code = httpURLConnection.getResponseCode();
            return code;
        }
        catch (Exception e){
            return -300;
        }
    }


    //发送PostJson请求
    public int PostJson(String BodyJson, String oriUrl){

        try{
            byte[] test = BodyJson.getBytes(StandardCharsets.UTF_8);
            int length = test.length;

            URL theUrl = new URL(oriUrl);
            URLConnection connection = theUrl.openConnection();

            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type","application/json");
            httpURLConnection.setRequestProperty("Accept","*/*");
            httpURLConnection.setFixedLengthStreamingMode(length);

            httpURLConnection.connect();

            try(OutputStream os = httpURLConnection.getOutputStream()){
                os.write(test);
            }

            int code = httpURLConnection.getResponseCode();
            return code;
        }
        catch (Exception e){
            return -300;
        }
    }

    public String PostImg(InputStream in,String oriUrl,String filename){
        try{
            URL theUrl =new URL(oriUrl);
            URLConnection urlConnection = theUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

            String boundary = UUID.randomUUID().toString();

            byte[] boundaryBytes =
                    ("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8);
            byte[] finishBoundaryBytes =
                    ("--" + boundary + "--").getBytes(StandardCharsets.UTF_8);
                    
            httpURLConnection.setRequestProperty("Content-Type",
                    "multipart/form-data; charset=UTF-8; boundary=" + boundary);


            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setChunkedStreamingMode(0);

            try(OutputStream out = httpURLConnection.getOutputStream()) {

                out.write(boundaryBytes);



                String o = "Content-Disposition: form-data; name=\"file\""
                        + "; filename=\"" + URLEncoder.encode(filename,"UTF-8") + "\"\r\n\r\n";

                out.write(o.getBytes(StandardCharsets.UTF_8));

                byte[] buffer = new byte[2048];
                for (int n = 0; n >= 0; n = in.read(buffer))
                    out.write(buffer, 0, n);

                out.write("\r\n".getBytes(StandardCharsets.UTF_8));

                out.write(finishBoundaryBytes);
            }

            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine())!= null){
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();

            String result = stringBuilder.toString();
            httpURLConnection.disconnect();
            return result;

        }
        catch (Exception e){
            e.getCause();
            e.printStackTrace();
            return "-300";
        }

    }


}
