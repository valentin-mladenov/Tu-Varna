-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[FindTestAttemtsUnderSuccessRate]
	@SuccessRate int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	Select t11.Test, CONCAT(t11.Score, ' %') AS Success
		FROM (SELECT t.Test, (
		(
			SELECT COUNT(IsCorrect) FROM dbo.TestAtmptData_View AS t2 WHERE t2.IsCorrect = 1 AND t2.Test = t.Test
		) * 100
		/ 
		(
			SELECT COUNT(IsCorrect) FROM dbo.TestAtmptData_View AS t1 WHERE t1.Test = t.Test
		)
		) as Score
		FROM dbo.TestAtmptData_View as t
		GROUP BY t.Test) as t11
	WHERE Score < @SuccessRate
END
