-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE   PROCEDURE [dbo].[FindTestAttemtCountForPeriodBySubject]
	@FromDate DATETIME2,
	@ToDate DATETIME2,
	@Subject NVARCHAR(50)
AS
BEGIN
	SET NOCOUNT ON;

	SELECT COUNT(t.TestAttemptId) AS [TestAttemptCount]
	FROM (
		SELECT  TestAttemptId
		FROM [dbo].[TestAtmptData_View]
		WHERE [Subject] = @Subject AND [Date] > @FromDate AND [Date] < @ToDate
		GROUP BY TestAttemptId
	) AS t
END
