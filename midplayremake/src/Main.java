import javax.sound.midi.InvalidMidiDataException;

public class Main {
    public static void main(String[] args) throws InvalidMidiDataException {
        PianoRoll pianoRoll = new PianoRoll(90, 0);
        Note[] lead = {
            //起来，饥寒交迫的奴隶，起来，全世界受苦的人
            Note.G_5,Note.C_6,Note.S_0,Note.B_5,Note.D_6,Note.C_6,Note.G_5,Note.E_5,
            Note.A_5,Note.S_0,Note.F_5,Note.S_0,Note.A_5,
            Note.D_6,Note.S_0,Note.C_6,Note.B_5,Note.A_5,Note.G_5,Note.F_5,
            Note.E_5,Note.S_0,

            //满腔的热血已经沸腾，要为真理而斗争
            Note.G_5,Note.C_6,Note.S_0,Note.B_5,Note.D_6,Note.C_6,Note.G_5,Note.E_5,Note.A_5,Note.S_0,
            Note.F_5,Note.S_0,Note.A_5,Note.D_6,Note.C_6,
            Note.B_5,Note.D_6,Note.F_6,Note.B_5,Note.C_6,Note.S_0,

            //旧世界打个落花流水，奴隶们起来起来
            Note.S_0,Note.E_6,Note.D_6,Note.B_5,Note.S_0,Note.A_5,Note.B_5,Note.C_6,Note.A_5,Note.B_5,Note.S_0,Note.G_5,
            Note.S_0,Note.G_5,Note.F_5,Note.G_5,Note.A_5,Note.S_0,Note.A_5,Note.D_6,Note.S_0,Note.C_6,Note.B_5,Note.S_0,

            //不要说我们一无所有，我们要做天下的主人
            Note.S_0,Note.D_6,Note.D_6,Note.S_0,
            Note.B_5,Note.G_5,Note.G_5,Note.F_5,Note.G_5,Note.E_6,Note.S_0,Note.C_6,
            Note.S_0,Note.A_5,Note.B_5,Note.C_6,Note.B_5,Note.D_6,Note.C_6,Note.A_5,Note.G_5,Note.S_0,
            
            //这是最后的斗争，团结起来到明天
            Note.S_0,Note.E_6,Note.S_0,Note.D_6,
            Note.C_6,Note.S_0,Note.G_5,Note.S_0,Note.E_5,Note.A_5,Note.S_0,Note.F_5,
            Note.S_0,Note.D_6,Note.S_0,Note.C_6,Note.B_5,Note.S_0,
            Note.A_5,Note.G_5,Note.G_5,Note.S_0,

            //英特纳雄耐尔就一定要实现
            Note.S_0,Note.G_5,Note.E_6,Note.S_0,Note.D_6,Note.G_5,
            Note.C_6,Note.S_0,Note.B_5,Note.S_0,Note.B_5,Note.A_5,Note.S_0,
            Note.G_5,Note.A_5,Note.D_6,Note.D_6,Note.S_0,

            Note.S_0,Note.E_6,Note.S_0,Note.D_6,
            Note.C_6,Note.S_0,Note.G_5,Note.S_0,Note.E_5,Note.A_5,Note.S_0,Note.F_5,
            Note.S_0,Note.D_6,Note.S_0,Note.C_6,Note.B_5,Note.S_0,
            Note.A_5,Note.G_5,Note.E_6,Note.S_0,

            Note.S_0,Note.E_6,Note.G_6,Note.S_0,
            Note.F_6,Note.E_6,Note.D_6,Note.S_0,
            Note.E_6,Note.F_6,Note.S_0,
            Note.F_6,Note.E_6,Note.S_0,
            Note.E_6,Note.D_6,Note.S_0,
            Note.D_6,Note.C_6,Note.S_0,Note.S_0,

        };
        int leadTicks[]={

            4,4,3,2,2,2,2,2,
            2,4,4,4,2,
            2,3,2,2,2,2,2,
            2,8,

            4,4,3,2,2,2,2,2,2,3,
            4,1,2,2,2,
            4,4,4,4,4,4,

            4,2,2,2,4,2,2,2,2,2,4,4,
            1,2,2,2,2,3,2,2,3,2,2,4,
         
            4,4,4,3,
            2,2,2,2,2,2,4,4,
            1,2,2,2,4,4,4,4,4,4,
           
            4,2,1,1,
            2,4,4,3,2,2,4,4,
            2,2,1,1,2,4,
            4,4,4,4,
            
            4,4,4,4,4,4,
            4,4,4,3,2,4,3,
            3,4,4,4,4,

            4,2,1,1,
            2,4,4,3,2,2,4,4,
            2,2,1,1,2,4,
            4,4,4,4,

            4,4,4,4,
            4,4,4,3,
            2,3,4,
            2,3,3,
            2,3,3,
            2,3,4,4,

        };
        pianoRoll.setNote(lead, 1, 80, leadTicks);


        pianoRoll.run();
    }

}