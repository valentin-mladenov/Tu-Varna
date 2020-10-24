namespace SentimentWeb.Service.Data.Entities
{
    public enum Sex
    {
        Female,
        Male
    }

    public enum AgeRange
    {
        From10Till15,
        From16Till19,
        From20Till25,
        From26Till35,
        From36Till45,
        From46Till55,
        From56Till65,
        From66ToInfinity,
    }

    public enum MaritalStatus
    {
        Single,
        Married,
        Devorced,
        Widowed
    }

    public class LanguageScore
    {
        public float BG { get; set; }
        public float DE { get; set; }
        public float EN { get; set; }
        public float RU { get; set; }
    }
}
