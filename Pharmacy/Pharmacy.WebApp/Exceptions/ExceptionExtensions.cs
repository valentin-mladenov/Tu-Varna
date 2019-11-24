using System;

namespace Pharmacy.WebApp.Exceptions
{
    public static class ExceptionExtensions
    {
        public static string GetFullMessage(this Exception ex)
        {
            return ex.InnerException == null
                 ? ex.Message
                 : ex.Message + " --> " + ex.InnerException.GetFullMessage();
        }

        public static string GetDeepestMessage(this Exception ex)
        {
            return ex.InnerException == null
                 ? ex.Message
                 : ex.InnerException.GetDeepestMessage();
        }
    }
}