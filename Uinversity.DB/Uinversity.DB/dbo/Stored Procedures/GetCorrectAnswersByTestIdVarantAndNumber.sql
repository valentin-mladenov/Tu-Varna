-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[GetCorrectAnswersByTestIdVarantAndNumber]
	@TestId nvarchar(50),
	@Variant int,
	@Number int
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	SELECT COUNT(t.TestId) AS 'CorrectAnswers'
	FROM dbo.TestData_View AS t
	WHERE t.TestId = @TestId AND t.Variant = @Variant AND t.Number = @Number AND IsCorrect = 1
END
