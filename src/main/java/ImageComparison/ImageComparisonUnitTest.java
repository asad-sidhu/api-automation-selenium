//package ImageComparison;
//
//import Config.BaseTest;
//import MiscFunctions.Constants;
//import PageObjects.InterventionManagementPage;
//import Test.Ancera.Login.LoginTest;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.github.romankh3.image.comparison.ImageComparison;
//import com.github.romankh3.image.comparison.model.ImageComparisonResult;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import static MiscFunctions.DateUtil.date;
//import static MiscFunctions.ExtentVariables.spark;
//import static com.github.romankh3.image.comparison.ImageComparisonUtil.readImageFromResources;
//import static com.github.romankh3.image.comparison.model.ImageComparisonState.MATCH;
//import static com.github.romankh3.image.comparison.model.ImageComparisonState.MISMATCH;
//
//public class ImageComparisonUnitTest extends BaseTest {
//
//    @BeforeTest(groups = {"smoke", "regression"})
//    public void extent() throws InterruptedException, IOException {
//        spark = new ExtentSparkReporter("target/Reports/Administration_Intervention_Management" + date + ".html");
//        spark.config().setReportName("Intervention Management Test Report");
////        DB_Config_DB.test();
//        System.out.println("Before Test");
//    }
//
//    @BeforeClass(groups = {"smoke", "regression"})
//    public void Login() throws InterruptedException, IOException {
//        LoginTest.login();
//        InterventionManagementPage.navigateInterventionMgtScreen();
//        System.out.println("Before Class");
//    }
//    @Test(priority = 1, enabled = true, description = "TC: IE-9411, 9412", groups = {"smoke","regression"})
//    public void VerifyInlineEditAccessRights() throws IOException, InterruptedException {
//        InterventionManagementPage imp = new InterventionManagementPage(getDriver());
////        imp.AccessRights();
//        LoginTest.login();
////        imp.inlineEditAccessRights();
////        imp.adminAccessRights();
//        LoginTest.login();
//    }
//    @Test(priority = 2, enabled = true)
//    public void shouldProperlyExecuteComparing() throws IOException {
//        //given
//        BufferedImage expectedResultImage = readImageFromResources(Constants.ReportFilePath+"/ScreenShots/result.png");
//
//        File file = new File("build/test-images/result.png");
//
//        get_Screenshot();
//        //when
//        ImageComparison imageComparison = new ImageComparison(Constants.ReportFilePath+"/ScreenShots/expected.png", Constants.ReportFilePath+"/ScreenShots/actual.png");
//        ImageComparisonResult imageComparisonResult = imageComparison.compareImages().writeResultTo(file);
//
//        //then
//        Assert.assertNotNull(imageComparison.getActual());
//        Assert.assertNotNull(imageComparison.getExpected());
//        Assert.assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
//        assertImagesEqual(expectedResultImage, imageComparisonResult.getResult());
//    }
//
//    @Test(priority = 3, enabled = true)
//    public void shouldAllowLessThanOnePercentDifference() {
//        //given
//        ImageComparison imageComparison = new ImageComparison("expected.png", "actual.png")
//                .setAllowingPercentOfDifferentPixels(1);
//
//        //when
//        ImageComparisonResult result = imageComparison.compareImages();
//
//        //then
//        Assert.assertEquals(MATCH, result.getImageComparisonState());
//
//        //when
//        result = imageComparison.setAllowingPercentOfDifferentPixels(0).compareImages();
//
//        //then
//        Assert.assertEquals(MISMATCH, result.getImageComparisonState());
//    }
////
////    @DisplayName("Should perform maximal rectangle count")
////    @Test
////    public void shouldPerformMaximalRectangleCount() {
////        //given
////        ImageComparison imageComparison = new ImageComparison("expected.png", "actual.png");
////        imageComparison.setMaximalRectangleCount(3);
////        BufferedImage expectedImage = readImageFromResources("maximalRectangleCountResult.png");
////
////        //when
////        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedImage, imageComparisonResult.getResult());
////    }
////
////    @DisplayName("Should reproduce Bug #165")
////    @Test
////    public void shouldReproduceBug165() {
////        //given
////        ImageComparison imageComparison = new ImageComparison("expected.png", "actual.png");
////        imageComparison.setMaximalRectangleCount(5);
////        BufferedImage expectedImage = readImageFromResources("result.png");
////
////        //when
////        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedImage, imageComparisonResult.getResult());
////    }
////
////    @DisplayName("Should properly compare totally different images")
////    @Test
////    public void shouldProperlyCompareTotallyDifferentImages() {
////        //when
////        BufferedImage expectedResult = readImageFromResources("totallyDifferentImageResult.png");
////
////        //when
////        ImageComparisonResult imageComparisonResult =
////                new ImageComparison("expected.png", "actualTotallyDifferent.png").compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedResult, imageComparisonResult.getResult());
////    }
////
////    @DisplayName("Should properly get destination")
////    @Test
////    public void shouldProperlyGetDestination() {
////        //given
////        BufferedImage expected = readImageFromResources("expected.png");
////        BufferedImage actual = readImageFromResources("actual.png");
////
////        //when
////        ImageComparison imageComparison = new ImageComparison(expected, actual)
////                .setDestination(new File("result.png"));
////
////        //then
////        assertTrue(imageComparison.getDestination().isPresent());
////    }
////
////    @DisplayName("Should properly work minimal rectangle size configuration")
////    @Test
////    public void shouldWorkMinimalRectangleSize() {
////        //given
////        ImageComparison imageComparison = new ImageComparison("expected.png", "actual.png");
////        imageComparison.setMinimalRectangleSize(4 * 4 + 1);
////        BufferedImage expectedImage = readImageFromResources("minimalRectangleSizeResult.png");
////
////        //when
////        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedImage, imageComparisonResult.getResult());
////    }
////
////    @DisplayName("should reproduce bug #17")
////    @Test
////    public void shouldReproduceBug17() {
////        //when
////        ImageComparisonResult imageComparisonResult = new ImageComparison("expected.png", "actual.png")
////                .compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertNotNull(imageComparisonResult.getResult());
////    }
////
////    @DisplayName("Should reproduce bug 201")
////    @Test
////    public void shouldReproduceBug201() {
////        //given
////        BufferedImage expectedResultImage = readImageFromResources("result.png");
////
////        //when
////        ImageComparisonResult imageComparisonResult =
////                new ImageComparison("expected.png", "actual.png").compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedResultImage, imageComparisonResult.getResult());
////    }
////
////    @DisplayName("Should reproduce bug 21")
////    @Test
////    public void shouldReproduceBug21() {
////        //given
////        BufferedImage expectedResultImage = readImageFromResources("result.png");
////
////        //when
////        ImageComparisonResult imageComparisonResult =
////                new ImageComparison("expected.png", "actual.png").compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedResultImage, imageComparisonResult.getResult());
////    }
////
////    @DisplayName("Should reproduce bug 11")
////    @Test
////    public void shouldReproduce11() {
////        //given
////        BufferedImage expectedResultImage = readImageFromResources("result.png");
////
////        BufferedImage expected = readImageFromResources("expected.png");
////        BufferedImage actual = readImageFromResources("actual.png");
////
////        //when
////        ImageComparisonResult imageComparisonResult = new ImageComparison(expected, actual).compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedResultImage, imageComparisonResult.getResult());
////    }
////
////    @DisplayName("Should properly work rectangle size width configuration")
////    @Test
////    public void shouldProperlyWorkRectangleLineWidth() {
////        //given
////        BufferedImage expectedResultImage = readImageFromResources("result.png");
////
////        BufferedImage expected = readImageFromResources("expected.png");
////        BufferedImage actual = readImageFromResources("actual.png");
////
////        ImageComparison imageComparison = new ImageComparison(expected, actual,
////                new File("build/test-images/result.png"))
////                .setRectangleLineWidth(10);
////
////        //when
////        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedResultImage, imageComparisonResult.getResult());
////        assertEquals(10, imageComparison.getRectangleLineWidth());
////    }
////
////    @DisplayName("Should properly populate rectangles")
////    @Test
////    public void shouldProperlyPopulateRectangles() {
////        //given
////        BufferedImage original = readImageFromResources("expected.png");
////        BufferedImage masked = readImageFromResources("actual.png");
////        List<Rectangle> expectedRectangleList = new ArrayList<>();
////        expectedRectangleList.add(new Rectangle(131, 0, 224, 224));
////        ImageComparison imageComparison = new ImageComparison(original, masked);
////
////        //when
////        List<Rectangle> actualRectangleList = imageComparison.createMask();
////
////        //then
////        assertEquals(1, actualRectangleList.size());
////        assertEquals(expectedRectangleList.get(0), actualRectangleList.get(0));
////    }
////
////    @DisplayName("Should properly work SIZE_MISMATCH state")
////    @Test
////    public void shouldProperlyWorkSizeMismatch() {
////        //given
////        BufferedImage expected = new BufferedImage(10, 10, 10);
////        BufferedImage actual = new BufferedImage(12, 12, 10);
////
////        //when
////        ImageComparisonResult imageComparisonResult = new ImageComparison(expected, actual).compareImages();
////
////        //then
////        assertEquals(SIZE_MISMATCH, imageComparisonResult.getImageComparisonState());
////    }
////
////    @DisplayName("Should ignore excluded areas")
////    @Test
////    public void shouldIgnoreExcludedAreas() {
////        //given
////        BufferedImage expected = readImageFromResources("expected.png");
////        BufferedImage actual = readImageFromResources("actual.png");
////        List<Rectangle> excludedAreas = new ArrayList<>();
////        excludedAreas.add(new Rectangle(131, 0, 224, 224));
////        ImageComparison imageComparison = new ImageComparison(expected, actual).setExcludedAreas(excludedAreas);
////
////        //when
////        ImageComparisonResult result = imageComparison.compareImages();
////
////        //then
////        assertEquals(result.getImageComparisonState(), MATCH);
////    }
////
////    @DisplayName("Should reproduce bug 98")
////    @Test
////    public void shouldReproduceBug98and97() {
////        //given
////        BufferedImage expected = readImageFromResources("expected.png");
////        BufferedImage actual = readImageFromResources("actual.png");
////
////        List<Rectangle> excludedAreas = asList(
////                new Rectangle(80, 388, 900, 514),
////                new Rectangle(410, 514, 900, 565),
////                new Rectangle(410, 636, 900, 754)
////        );
////
////        BufferedImage expectedImage = readImageFromResources("result.png");
////
////        ImageComparison imageComparison = new ImageComparison(expected, actual)
////                .setExcludedAreas(excludedAreas)
////                .setRectangleLineWidth(5)
////                .setDrawExcludedRectangles(true);
////
////        //when
////        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
////
////        //then
////        assertEquals(MATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedImage, imageComparisonResult.getResult());
////    }
////
////    @DisplayName("Should properly compare in bug #171")
////    @Test
////    public void shouldProperlyCompareBug171() {
////        //given
////        BufferedImage actual = readImageFromResources("actual.png");
////        BufferedImage expected = readImageFromResources("expected.png");
////
////        BufferedImage expectedResultImage = readImageFromResources("result.png");
////
////        ImageComparison imageComparison = new ImageComparison(expected, actual)
////                .setExcludedAreas(singletonList(new Rectangle(325, 50, 650, 80)))
////                .setDrawExcludedRectangles(true)
////                .setRectangleLineWidth(3);
////
////        //when
////        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
////
////        //then
////        assertEquals(MATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedResultImage, imageComparisonResult.getResult());
////    }
////
////    @DisplayName("Should properly compare bug #113")
////    @Test
////    public void shouldProperlyCompareBug113() {
////        //given
////        BufferedImage expected = readImageFromResources("expected.png");
////        BufferedImage actual = readImageFromResources("actual.png");
////
////        List<Rectangle> excludedAreas = asList(
////                new Rectangle(410, 514, 900, 565),
////                new Rectangle(410, 636, 900, 754)
////        );
////
////        BufferedImage expectedImage = readImageFromResources("result.png");
////
////        ImageComparison imageComparison = new ImageComparison(expected, actual)
////                .setExcludedAreas(excludedAreas)
////                .setRectangleLineWidth(5)
////                .setPixelToleranceLevel(0.0)
////                .setDrawExcludedRectangles(true);
////
////        //when
////        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedImage, imageComparisonResult.getResult());
////        assertEquals(0.0, imageComparison.getPixelToleranceLevel(), 0.0);
////    }
////
////    @DisplayName("Should properly fill in transparent red")
////    @Test
////    public void shouldProperlyFillInTransparentRed() {
////        //given
////        BufferedImage expected = readImageFromResources("expected.png");
////        BufferedImage actual = readImageFromResources("actual.png");
////
////        List<Rectangle> excludedAreas = asList(
////                new Rectangle(410, 514, 900, 565),
////                new Rectangle(410, 636, 900, 754)
////        );
////
////        BufferedImage expectedImage = readImageFromResources("result.png");
////
////        ImageComparison imageComparison = new ImageComparison(expected, actual)
////                .setExcludedAreas(excludedAreas)
////                .setRectangleLineWidth(5)
////                .setPixelToleranceLevel(0.0)
////                .setDrawExcludedRectangles(true)
////                .setDifferenceRectangleFilling(true, 30.0);
////
////        //when
////        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedImage, imageComparisonResult.getResult());
////    }
////
////    @DisplayName("Should properly fill in transparent green")
////    @Test
////    public void shouldProperlyFillInTransparentGreen() {
////        //given
////        BufferedImage expected = readImageFromResources("expected.png");
////        BufferedImage actual = readImageFromResources("actual.png");
////
////        List<Rectangle> excludedAreas = asList(
////                new Rectangle(410, 514, 900, 565),
////                new Rectangle(410, 636, 900, 754)
////        );
////
////        BufferedImage expectedImage = readImageFromResources("result.png");
////
////        ImageComparison imageComparison = new ImageComparison(expected, actual)
////                .setExcludedAreas(excludedAreas)
////                .setRectangleLineWidth(5)
////                .setPixelToleranceLevel(0.0)
////                .setDrawExcludedRectangles(true)
////                .setExcludedRectangleFilling(true, 30.0);
////
////        //when
////        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedImage, imageComparisonResult.getResult());
////    }
////
////    @DisplayName("Should properly compare in bug #134")
////    @Test
////    public void shouldProperlyCompareInBug134() {
////        //given
////        ImageComparison imageComparison = new ImageComparison("expected.png", "actual.png");
////        BufferedImage expectedImage = readImageFromResources("result.png");
////
////        //when
////        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedImage, imageComparisonResult.getResult());
////    }
////
////    @DisplayName("Should properly get pixel differences")
////    @Test
////    public void shouldProperlyHandleIssue196() {
////        //given
////        ImageComparison imageComparison = new ImageComparison("expected.png", "actual.png");
////        BufferedImage expectedImage = readImageFromResources("result.png");
////
////        //when
////        ImageComparisonResult imageComparisonResult = imageComparison.compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertEquals(0.4274517595767975, imageComparisonResult.getDifferencePercent());
////        assertImagesEqual(expectedImage, imageComparisonResult.getResult());
////    }
////
////    @DisplayName("Should match size")
////    @Test
////    public void shouldMatchSize() {
////        //when
////        ImageComparisonResult imageComparisonResult = new ImageComparison("expected.png", "expected.png")
////                .compareImages();
////
////        //then
////        assertEquals(MATCH, imageComparisonResult.getImageComparisonState());
////        assertEquals(0, imageComparisonResult.getRectangles().size());
////    }
////
////    @DisplayName("Should properly work getters and setters")
////    @Test
////    public void shouldProperlyWorkGettersAndSetters() {
////        //when
////        ImageComparison imageComparison = new ImageComparison("expected.png", "actual.png")
////                .setMinimalRectangleSize(100)
////                .setMaximalRectangleCount(200)
////                .setRectangleLineWidth(300)
////                .setExcludedAreas(asList(Rectangle.createZero(), Rectangle.createDefault()))
////                .setDrawExcludedRectangles(true)
////                .setPixelToleranceLevel(0.6)
////                .setThreshold(400)
////                .setDifferenceRectangleFilling(true, 35.1)
////                .setExcludedRectangleFilling(true, 45.1)
////                .setAllowingPercentOfDifferentPixels(48.15)
////                .setDifferenceRectangleColor(Color.BLACK)
////                .setExcludedRectangleColor(Color.BLUE);
////
////        //then
////        assertEquals(String.valueOf(100), String.valueOf(imageComparison.getMinimalRectangleSize()));
////        assertEquals(String.valueOf(200), String.valueOf(imageComparison.getMaximalRectangleCount()));
////        assertEquals(String.valueOf(300), String.valueOf(imageComparison.getRectangleLineWidth()));
////        assertEquals(String.valueOf(400), String.valueOf(imageComparison.getThreshold()));
////        assertEquals(0.6, imageComparison.getPixelToleranceLevel(), 0.0);
////        assertTrue(imageComparison.isDrawExcludedRectangles());
////        assertEquals(35.1, imageComparison.getPercentOpacityDifferenceRectangles(), 0.0);
////        assertEquals(45.1, imageComparison.getPercentOpacityExcludedRectangles(), 0.0);
////        assertTrue(imageComparison.isFillDifferenceRectangles());
////        assertTrue(imageComparison.isFillExcludedRectangles());
////        assertEquals(48.15, imageComparison.getAllowingPercentOfDifferentPixels(), 0.0);
////        assertEquals(Color.BLACK, imageComparison.getDifferenceRectangleColor());
////        assertEquals(Color.BLUE, imageComparison.getExcludedRectangleColor());
////
////    }
////
////    @DisplayName("Should properly compare in JPEG extension")
////    @Test
////    public void shouldProperlyCompareInJpeg() {
////        //given
////        BufferedImage expected = readImageFromResources("expected.jpg");
////        BufferedImage actual = readImageFromResources("actual.jpg");
////
////        BufferedImage expectedResult = readImageFromResources("result.jpg");
////
////        //when
////        ImageComparisonResult imageComparisonResult = new ImageComparison(expected, actual)
////                .setMinimalRectangleSize(4)
////                .compareImages();
////
////        //then
////        assertEquals(MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertImagesEqual(expectedResult, imageComparisonResult.getResult());
////    }
////
////    @DisplayName("Should properly compare mis size")
////    @Test
////    public void shouldProperlyCompareMisSizedImages() {
////        //given
////        BufferedImage expected = readImageFromResources("expected.png");
////        BufferedImage actual = readImageFromResources("actual.png");
////
////        //when
////        ImageComparisonResult imageComparisonResult = new ImageComparison(expected, actual).compareImages();
////
////        //then
////        assertEquals(SIZE_MISMATCH, imageComparisonResult.getImageComparisonState());
////        assertEquals(0, imageComparisonResult.getRectangles().size());
////        boolean differenceLessThan2 = imageComparisonResult.getDifferencePercent() < 2;
////        assertTrue(differenceLessThan2);
////    }
////
//    private void assertImagesEqual(BufferedImage expected, BufferedImage actual) {
//        if (expected.getWidth() != actual.getWidth() || expected.getHeight() != actual.getHeight()) {
//            System.out.println(expected.getWidth());
//            System.out.println(expected.getHeight());
//            System.out.println(actual.getWidth());
//            System.out.println(actual.getHeight());
////            fail("Images have different dimensions");
//        }
//
//        int width = expected.getWidth();
//        int height = expected.getHeight();
//
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                if (expected.getRGB(x, y) != actual.getRGB(x, y)) {
////                    fail("Images are different, found a different pixel at: x = " + x + ", y = " + y);
//                }
//            }
//        }
//    }
//}
