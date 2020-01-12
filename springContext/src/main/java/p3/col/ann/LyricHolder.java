package p3.col.ann;

import org.springframework.stereotype.Service;

@Service(value="lyricHolder")
public class LyricHolder {
    private String value = "'You be the DJ. I'll be the driver";

    @Override
    public String toString() {
        return "LyricHolder{" +
                "value='" + value + '\'' +
                '}';
    }
}
