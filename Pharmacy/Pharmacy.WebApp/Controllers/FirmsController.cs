using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Pharmacy.WebApp.Models;
using Pharmacy.WebApp.Models.DataAccessLayer;

namespace Pharmacy.WebApp.Controllers
{
    public class FirmsController : Controller
    {
        private PharmacyDbContext db = new PharmacyDbContext();

        // GET: Firms
        public ActionResult Index(string searchString)
        {
            if (string.IsNullOrWhiteSpace(searchString))
            {
                searchString = "";
            }

            var firms = db.Firms
                .ToList()
                .Where(f => f.ToString().Contains(searchString))
                .OrderByDescending(f => f.Stringed)
                .ToList();

            return View(firms);
        }

        // GET: Firms/Details/5
        public ActionResult Details(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Firm firm = db.Firms.Find(id);
            if (firm == null)
            {
                return HttpNotFound();
            }
            return View(firm);
        }

        // GET: Firms/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Firms/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,Address,Name,Code,EIK")] Firm firm)
        {
            var found = db.Firms.Where(f => f.EIK == firm.EIK).FirstOrDefault();

            if (found != null)
            {
                ModelState.AddModelError("EIK", "EIK already exists!!!");
            }

            if (!this.IsRealEIK(firm.EIK))
            {
                ModelState.AddModelError("EIK", "EIK is fake!!!");
            }

            if (ModelState.IsValid)
            {
                firm.Id = Guid.NewGuid();
                db.Firms.Add(firm);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            return View(firm);
        }

        // GET: Firms/Edit/5
        public ActionResult Edit(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Firm firm = db.Firms.Find(id);
            if (firm == null)
            {
                return HttpNotFound();
            }
            return View(firm);
        }

        // POST: Firms/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,Address,Name,Code,EIK")] Firm firm)
        {
            var found = db.Firms.Where(f => f.EIK == firm.EIK && f.Id != firm.Id).FirstOrDefault();

            if (found != null)
            {
                ModelState.AddModelError("EIK", "EIK already exists!!!");
            }

            if (!this.IsRealEIK(firm.EIK))
            {
                ModelState.AddModelError("EIK", "EIK is fake!!!");
            }

            if (ModelState.IsValid)
            {
                db.Entry(firm).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(firm);
        }

        // GET: Firms/Delete/5
        public ActionResult Delete(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Firm firm = db.Firms.Find(id);
            if (firm == null)
            {
                return HttpNotFound();
            }
            return View(firm);
        }

        // POST: Firms/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(Guid id)
        {
            Firm firm = db.Firms.Find(id);
            try
            {
                db.Firms.Remove(firm);
                db.SaveChanges();

                return RedirectToAction("Index");
            }
            catch (InvalidOperationException ex)
            {
                ViewBag.Error = "Delete cannot be Done! Remove all releted deliveries and sells!";

                return View(firm);
            }
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool IsRealEIK(long eik)
        {
            var eikIntArray = Array.ConvertAll(eik.ToString().ToCharArray(), c => (int)Char.GetNumericValue(c));

            var result = (decimal)eikIntArray.Select((value, index) =>
            {
                if (index == eikIntArray.Length - 1) return 0;

                return (decimal)value * (index + 1);
            }).Sum() / 11;

            if (result > 10)
            {
                return false;
            }

            if (result == 10)
            {
                var result2 = (decimal)eikIntArray.Select((value, index) =>
                {
                    if (index == eikIntArray.Length - 1) return 0;

                    return (decimal)value * (index + 3);
                }).Sum() / 11;

                if (result2 > 10)
                {
                    return false;
                }

                if (result2 == 10 && (decimal)eikIntArray[eikIntArray.Length - 1] == 0)
                {
                    return true;
                }

                if (result2 == (decimal)eikIntArray[eikIntArray.Length - 1])
                {
                    return true;
                }

                return false;
            }

            if (result == (decimal)eikIntArray[eikIntArray.Length - 1])
            {
                return true;
            }

            return false;
        }
    }
}
