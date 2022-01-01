import java.io.FileInputStream;
import java.util.Arrays;

public class DocLine {
    byte[] content;
    int length;
    int type; // 行类型判定。可自行通过行的特征进行相关代码的补写。

    public DocLine(FileInputStream fileInputStream) {
        try {
            byte[] temp = new byte[fileInputStream.available() + 4]; // 初始化一个临时存放剩余文本值的Byte数组，最后用于本行文本数据值的赋值
            int dataByte = 0; // 单字节值的存放量，同时作为判断文本是否读完的量值
            int count = 0; // 计数，同时用于标志本行文本所占字节量
            while ((dataByte = fileInputStream.read()) != -1) {

                // 对于本身为空行者。直接结束构建，并设置content value为0
                if (dataByte == 13 && (fileInputStream.read() == 10)) {
                    if (count == 0) {
                        this.content = new byte[1];
                        this.content[0] = 0;
                        break;
                    } else {
                        // 正常结束。
                        this.content = new byte[count];
                        this.content = Arrays.copyOf(temp, count);
                        this.length = this.content.length;
                        break;
                    }
                } else {
                    temp[count] = (byte) dataByte;
                    count++;
                }
            }
        } catch (Exception e) {
            e.getCause();
        }

    }

    public int getLength() {
        return this.length;
    }

    public byte[] getContent() {
        return this.content;
    }

    public byte getContent(int i){
        return this.content[i];
    }

    public void setType(){
        if(this.content[0] == 0){
            this.type = 0;
        }
        else if(this.content[0] == 114){
            this.type = 2;
        }
        else{
            this.type = 1;
        }
    }

    public byte[] getPreEigenBytes(byte oneByte) {
        int tempCount = 0;
        byte[] results;

        while (tempCount < length) {
            if (this.content[tempCount] == oneByte) {
                results = Arrays.copyOf(this.content, tempCount);
                return results;
            }
            tempCount++;
        }
        return null;
    }

    public byte[] getPreEigenBytes(byte[] bytes) {
        int maxPoi = bytes.length;
        int point = 0;
        int tempCount = 0;

        byte[] results;

        while (tempCount < length) {
            if (this.content[tempCount] == bytes[point]) {
                int beginIndex = tempCount;
                point++;
                tempCount++;
                while (point < maxPoi) {
                    if (this.content[tempCount] == bytes[point] && (point + 1) == maxPoi) {
                        results = Arrays.copyOfRange(this.content, 0, beginIndex);
                        return results;
                    } else if (this.content[tempCount] == bytes[point] && (point + 1) != maxPoi) {
                        point++;
                        tempCount++;
                        continue;
                    } else {
                        point = 0;
                        tempCount++;
                        break;
                    }
                }
            }
            tempCount++;
        }
        return null;
    }

    public byte[] getPostEigenBytes(byte oneByte) {
        int tempCount = 0;
        byte[] results;

        while (tempCount < length) {
            if (this.content[tempCount] == oneByte) {
                results = Arrays.copyOfRange(this.content, tempCount + 1, length);
                return results;
            }
            tempCount++;
        }
        return null;
    }

    public byte[] getPostEigenBytes(byte[] bytes) {
        int maxPoi = bytes.length;
        int point = 0;
        int tempCount = 0;

        byte[] results;

        while (tempCount < length) {
            if (this.content[tempCount] == bytes[point]) {
                point++;
                tempCount++;
                while (point < maxPoi) {
                    if (this.content[tempCount] == bytes[point] && (point + 1) == maxPoi) {
                        results = Arrays.copyOfRange(this.content, tempCount + 1, length);
                        return results;
                    } else if (this.content[tempCount] == bytes[point] && (point + 1) != maxPoi) {
                        point++;
                        tempCount++;
                        continue;
                    } else {
                        point = 0;
                        tempCount++;
                        break;
                    }
                }
            }
            tempCount++;
        }
        return null;
    }

}
